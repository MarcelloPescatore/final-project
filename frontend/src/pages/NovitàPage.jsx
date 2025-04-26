import { useContext, useMemo, useState, useEffect } from 'react'
import DataContext from '../context/DataContext'
import { Link } from 'react-router-dom';

export default function NovitàPage() {
    const { data, loading, error } = useContext(DataContext);

    /* funzione per votazione in stelle */
    const renderStars = (vote) => {
        const fullStars = Math.floor(vote);
        const hasHalfStar = vote % 1 >= 0.5;
        const emptyStars = 5 - Math.ceil(vote);
        const stars = [];

        // Aggiungi le stelle piene
        for (let i = 0; i < fullStars; i++) {
            stars.push(<i key={`full-${i}`} className="bi bi-star-fill text-warning me-2"></i>);
        }

        // Aggiungi la mezza stella, se presente
        if (hasHalfStar) {
            stars.push(<i key="half" className="bi bi-star-half text-warning me-2"></i>);
        }

        // Aggiungi le stelle vuote
        for (let i = 0; i < emptyStars; i++) {
            stars.push(<i key={`empty-${i}`} className="bi bi-star text-warning me-2"></i>);
        }

        return stars;
    }
    /* i più recenti  */
    const giochiRecenti = useMemo(() => {
        if (!data || data.length === 0) return [];

        return [...data]
            .sort((a, b) => new Date(b.dataUscita) - new Date(a.dataUscita))
            .slice(0, 5);
    }, [data]);


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

            <div className='d-flex flex-column px-5 my-5'>

                <h2 className='cl_text'>Le Novità del Momento 🔥</h2>

                {/* videogames list */}
                <div className='d-flex mt-4 col-12 '>
                    {/* card */}
                    <div className='d-flex flex-wrap gap-5' >
                        {
                            giochiRecenti.map((videogioco) => (
                                <div className="card col-5 card-videogioco rounded" style={{ width: '19rem' }} key={videogioco.id} >
                                    <img src={videogioco.copertinaUrl} alt="..." className='rounded' />
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
                </div>
            </div>
        ))
}