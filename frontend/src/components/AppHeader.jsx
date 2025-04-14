import React, { useState } from 'react';

export default function AppHeader() {


    return (
        <header className='d-flex justify-content-between px-sm-5 py-4 align-items-center'>
            <div>
                <span className='fs-4' >GameLand</span >
            </div>

            <div>
                <ul className='d-flex gap-4 align-items-center m-0 p-0'>
                    <li>
                        <a className='' href="/videogiochi">Giochi</a>
                    </li>
                    <li>
                        <a className='' href="/novità">Novità</a>
                    </li>
                    <li>
                        <a className='' href="/contatti">Contatti</a>
                    </li>
                </ul>
            </div>
        </header>
    )
}