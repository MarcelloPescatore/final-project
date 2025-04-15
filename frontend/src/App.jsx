import React from "react"
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { DataProvider } from "./context/DataContext";
import Layout from "./pages/Layout";
import HomePage from "./pages/HomePage";
import NotFound from "./pages/NotFound";
import VideogiochiPage from "./pages/VideogiochiPage"
import VideogiocoDetails from "./pages/VideogiocoDetails"
import NovitàPage from "./pages/NovitàPage"
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import './App.css'

function App() {

  return (
    <DataProvider >
      <BrowserRouter>
        <Routes>
          <Route element={<Layout />}>
            <Route path="/" element={<HomePage />} />
            <Route path="/videogiochi" element={<VideogiochiPage />} />
            <Route path="/videogioco/:id" element={<VideogiocoDetails />} />
            <Route path="/novità" element={<NovitàPage />} />
            <Route path="*" element={<NotFound />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </DataProvider>
  )
}

export default App
