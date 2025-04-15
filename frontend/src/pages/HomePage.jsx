import { useContext, useMemo, useState, useEffect } from 'react'
import DataContext from '../context/DataContext'
import { Link } from 'react-router-dom';

export default function HomePage() {
    const { data, loading, error } = useContext(DataContext);

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


    return (
        <div id="homepage">
            <div className="image-wrapper">
                <img src="/sfondo.jpg" alt="sfondo" />
                <div className="fade-bottom"></div>
            </div>
            <div className="jumbotron d-flex align-items-center justify-content-center">
                <h2>Benvenuto su GameLand</h2>
            </div>

            <div className="mx-5 position-relative z-2" id='catalogo'>
                {loading ? (
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

                    <div className='d-flex flex-column my-5'>

                        <h3 className='cl_text'>Catalogo Videogiochi</h3>

                        {/* videogames list */}
                        <div className='d-flex flex-column mt-4 col-12 '>
                            {/* card */}
                            <div className='d-flex justify-content-center flex-wrap gap-5' >
                                {
                                    data.slice(0, 10).map((videogioco) => (
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
                            <div className='d-flex align-items-center justify-content-center mt-5'>
                                {/* mostra di più */}
                                <Link to={'/videogiochi'} className='fw-bold'>
                                    <button className='btn btn-outline-primary fs-4 px-4 py-2'>
                                        Vedi tutti
                                    </button>
                                </Link>
                            </div>

                        </div>
                    </div>
                )}
            </div>
        </div>
    )
}