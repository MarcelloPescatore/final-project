import { useContext, useMemo, useState, useEffect } from 'react'
import { useParams } from "react-router-dom"
import DataContext from "../context/DataContext"

export default function VideogiocoDetails() {
    const { id } = useParams();
    const { data, loading, error } = useContext(DataContext);

    const videogioco = data.find((videogioco) => videogioco.id == id);

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


    return (
        loading ? (
            <div className="d-flex justify-content-center align-items-center col-12 vh-100">
                <div className="spinner-border text-primary" role="status">
                    <span className="visually-hidden">Caricamento...</span>
                </div>
            </div>
        ) : error ? (
            <div className="d-flex justify-content-center align-items-center col-12 vh-100">
                <div className="alert alert-danger text-center fs-5 px-5 py-4" role="alert">
                    Ci scusiamo per il disagio, si è verificato un errore. <br />
                    Ricaricare la pagina, grazie.
                </div>
            </div>
        ) : (
            <div className='d-flex container my-5'>
                {
                    videogioco ? (
                        <div className="d-flex w-100 gap-5" id='videogioco_details'>
                            <div className="col-6 d-flex flex-column justify-content-start">
                                <h2>{videogioco.titolo}</h2>
                                <p><strong>Genere:</strong> {videogioco.generi.map(genere => genere.nome).join(", ")}</p>
                                <p><strong>Piattaforme:</strong> {videogioco.console.map(console => console.nome).join(", ")}</p>
                                <p><strong>Data di uscita:</strong> {videogioco.dataUscita}</p>
                                <div className="mb-3">
                                    {renderStars(videogioco.voto)} <span>({videogioco.voto})</span>
                                </div>
                                <p>{videogioco.descrizione}</p>
                            </div>

                            <div className="col-6">
                                <img
                                    src={videogioco.copertinaUrl || logo}
                                    alt="Copertina del videogioco"
                                    className="img-fluid rounded shadow"
                                />
                            </div>
                        </div>
                    ) : (
                        <div className="w-100 d-flex justify-content-center align-items-center">
                            <h3 className="text-danger">Videogioco non trovato</h3>
                        </div>
                    )
                }
            </div>
        ))
}