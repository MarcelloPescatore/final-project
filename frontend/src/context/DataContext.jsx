import React, { createContext, useState, useEffect } from 'react';
import axios from 'axios';

const DataContext = createContext();

export const DataProvider = ({ children }) => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const baseURL = import.meta.env.VITE_API_BASE_URL;


    useEffect(() => {
        axios.get(baseURL)
            .then(res => {
                setData(res.data);  
                setLoading(false);   
            })
            .catch(err => {
                setError(err);       
                setLoading(false);   
            });
    }, []);


    return (
        <DataContext.Provider value={{ data, loading, error }}>
            {children}
        </DataContext.Provider>
    );
};


export default DataContext;

























