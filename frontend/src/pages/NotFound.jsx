import { Link } from "react-router-dom"

export default function NotFound(){

    return(

        <div className="notFound flex-grow-1">
            <h1 className="pb-4">Qualcosa è andato storto, ci scusiamo per il disagio.</h1>
            <Link to={"/"}>
                <button className="btn btn-outline-primary">Torna all'Hompage</button>
            </Link>
        </div>
    )
}