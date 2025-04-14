import React, { useState } from 'react';
import logo from '../assets/images/logo.png';

export default function AppHeader() {


    return (
        <header className='d-flex justify-content-between px-sm-5 py-4 align-items-center'>
            <div>
                <a href="/">
                    <img src={logo} alt="logo" />
                </a>
            </div>

            <div>
                <ul className='d-flex gap-4 align-items-center m-0 p-0'>
                    <li>
                        <a href="/videogiochi">Giochi</a>
                    </li>
                    <li>
                        <a href="/novità">Novità</a>
                    </li>
                </ul>
            </div>
        </header>
    )
}