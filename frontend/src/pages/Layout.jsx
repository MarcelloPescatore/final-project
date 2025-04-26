import { Outlet } from "react-router-dom";
import AppFooter from "../components/AppFooter";
import AppHeader from "../components/AppHeader";


export default function Layout() {

    return (
        <>
            <AppHeader />
            <div className="d-flex flex-grow-1">
                <Outlet />
            </div>
            <AppFooter />
        </>
    )
}