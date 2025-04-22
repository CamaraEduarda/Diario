import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Postagem from './components/Postagem';
import DetalhesPostagem from './components/DetalhesPostagem';

function App() {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/" element={<Postagem />} />
                    <Route path="/postagem/:id" element={<DetalhesPostagem />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
