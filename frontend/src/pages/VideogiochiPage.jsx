import { useContext, useMemo, useState, useEffect } from 'react'
import { Link } from "react-router-dom";
import DataContext from '../context/DataContext'
import logo from '../assets/images/logo.png'

export default function VideogiochiPage() {
    const { data, loading, error } = useContext(DataContext);
    const [filtriSelezionati, setFiltriSelezionati] = useState({
        generi: [],
        piattaforme: []
    });;
    const [inputText, setInputText] = useState('');
    const [searchText, setSearchText] = useState('');
    const [numeroMostrati, setNumeroMostrati] = useState(10);

    /* mostra altri */
    const mostraAltri = () => {
        setNumeroMostrati(prev => prev + 20);
    };

    /* funzione per votazione in stelle */
    const renderStars = (vote) => {
        const fromeOneToFive = Math.ceil(vote * 0.5)
        const stars = []

        for (let i = 1; i <= 5; i++) {
            if (i <= fromeOneToFive) {
                stars.push(<i key={i} className="bi bi-star-fill text-warning me-2"></i>);
            } else {
                stars.push(<i key={i} className="bi bi-star text-warning me-2"></i>);
            }
        }

        return stars
    }

    /* estraggo i generi in modo univoco */
    const genereUnivoco = useMemo(() => {
        if (!data || data.length === 0) return [];

        const generiMap = new Map();

        data.forEach(videogioco => {
            videogioco.generi.forEach(genere => {
                generiMap.set(genere.id, genere);
            });
        });

        return Array.from(generiMap.values());
    }, [data]);

    /* estraggo le console in modo univoco */
    const consoleUnivoche = useMemo(() => {
        if (!data || data.length === 0) return [];

        const consoleMap = new Map();

        data.forEach(videogioco => {
            videogioco.console.forEach(console => {
                consoleMap.set(console.id, console);
            });
        });

        return Array.from(consoleMap.values());
    }, [data]);


    /* filtro Generi, Console e nome videogiochi */
    const handleFiltriChange = (tipo, nome) => {
        setFiltriSelezionati(prev => ({
            ...prev,
            [tipo]: prev[tipo].includes(nome)
                ? prev[tipo].filter(item => item !== nome)
                : [...prev[tipo], nome]
        }));
    };

    const handleSearch = (e) => {
        e.preventDefault();
        setSearchText(inputText);
        resetFiltri();
    };

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            handleSearch(e);
        }
    };

    const handleCancelSearch = () => {
        setInputText('');
        setSearchText('');
    };

    const normalizzaTesto = (text) => {
        // Rimuovo trattini e converte tutto in minuscolo
        return text.replace(/-/g, '').toLowerCase();
    };

    const videogiochiFiltrati = useMemo(() => {
        if (!data) return [];

        if (searchText.trim() !== '') {
            const testoNormalizzato = normalizzaTesto(searchText);

            // Filtra i videogiochi per nome
            return data.filter(videogioco =>
                normalizzaTesto(videogioco.titolo).includes(testoNormalizzato)
            );
        }

        return data.filter(videogioco => {

            const matchGeneri = filtriSelezionati.generi.length === 0 ||
                filtriSelezionati.generi.every(filtro =>
                    videogioco.generi.some(genere => genere.nome === filtro)
                );

            const matchPiattaforme = filtriSelezionati.piattaforme.length === 0 ||
                filtriSelezionati.piattaforme.every(filtro =>
                    videogioco.console.some(console => console.nome === filtro)
                );

            // Entrambi i filtri devono essere soddisfatti
            return matchGeneri && matchPiattaforme;
        });
    }, [data, filtriSelezionati, searchText]);

    const resetFiltri = () => {
        setFiltriSelezionati({
            generi: [],
            piattaforme: [],
        });
    };



    return (
        loading ? (
            <div className="d-flex justify-content-center align-items-center vh-100">
                <div className="spinner-border text-primary" role="status">
                    <span className="visually-hidden">Caricamento...</span>
                </div>
            </div>
        ) : error ? (
            <div className="d-flex justify-content-center align-items-center vh-100">
                <div className="alert alert-danger text-center fs-5 px-5 py-4" role="alert">
                    Ci scusiamo per il disagio, si è verificato un errore. <br />
                    Ricaricare la pagina, grazie.
                </div>
            </div>
        ) : (

            <div className='d-flex justify-content-between px-5 my-5'>
                {/* filter */}
                <div className='col-2 pe-2 sidebar'>
                    <h2>Aggiungi Filtri</h2>
                    <ul className='my-3'>
                        <li className='generi my-3'>
                            <span className='fs-4'>Generi</span>
                            <ul className='my-1'>
                                {
                                    genereUnivoco.map((genere) => (
                                        <li key={genere.id}>
                                            <label>
                                                <input
                                                    className='me-2'
                                                    type="checkbox"
                                                    checked={filtriSelezionati.generi.includes(genere.nome)}
                                                    onChange={() => handleFiltriChange('generi', genere.nome)}
                                                />
                                                {genere.nome}
                                            </label>
                                        </li>
                                    ))
                                }
                            </ul>
                        </li>
                        <li>
                            <span className='fs-4'>Piattaforme</span>
                            <ul className='my-1'>
                                {
                                    consoleUnivoche.map((console) => (
                                        <li key={console.id}>
                                            <label>
                                                <input
                                                    className='me-2'
                                                    type="checkbox"
                                                    checked={filtriSelezionati.piattaforme.includes(console.nome)}
                                                    onChange={() => handleFiltriChange('piattaforme', console.nome)}
                                                />
                                                {console.nome}
                                            </label>
                                        </li>
                                    ))
                                }
                            </ul>
                        </li>
                        <button className='btn btn-danger mt-3' onClick={resetFiltri}>RImuovi Filtri</button>
                    </ul>
                </div>

                {/* videogames list */}
                <div className='d-flex flex-column col-10 '>
                    {/* search bar */}
                    <form
                        className='d-flex justify-content-end align-items-center mb-5 gap-2'
                        id='search-bar'
                        onSubmit={handleSearch}
                    >
                        <input
                            type="text"
                            className='col-5 rounded px-3 h-100'
                            placeholder="Cerca videogiochi..."
                            value={inputText}
                            onChange={(e) => setInputText(e.target.value)}
                            onKeyDown={handleKeyDown}
                        />
                        <div className='d-flex gap-2 justify-content-end'>
                            <button type="submit" className='btn btn-primary'>Cerca</button>
                            <button type="button" className='btn btn-danger' onClick={handleCancelSearch}>Annulla</button>
                        </div>
                    </form>

                    {/* card */}
                    <div className='d-flex mx-5 gap-5 flex-wrap' >
                        {
                            videogiochiFiltrati.slice(0, numeroMostrati).map((videogioco) => (
                                <div className="card col-4 card-videogioco rounded" style={{ width: '19rem' }} key={videogioco.id} >
                                    <img src={videogioco.copertinaUrl ? videogioco.copertinaUrl : logo} alt="Copertina videogioco" className='rounded' />
                                    <div className="card-body d-flex flex-column  justify-content-end py-4">
                                        <div>
                                            <h5 className="card-title">{videogioco.titolo}</h5>
                                            <span>{renderStars(videogioco.voto)}</span>
                                        </div>
                                        <Link to={`/videogioco/${videogioco.id}`}>
                                            <button className="btn btn-primary mt-3 fw-bold">Scopri di più</button>
                                        </Link>
                                    </div>
                                </div>
                            ))
                        }
                    </div>
                    {
                        numeroMostrati < videogiochiFiltrati.length && (
                            <div className='d-flex justify-content-center w-100 mt-4'>
                                <button className='btn btn-outline-primary' onClick={mostraAltri}>
                                    Mostra altri 20 risultati
                                </button>
                            </div>
                        )
                    }
                </div>
            </div>
        ))
}