// App.js
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Chat from './components/Chat';
import LandingPage from './components/LandingPage.jsx';
import Login from './components/LoginPage';
import Registration from './components/RegistrationPage';
import './App.css';

function App() {
    return (
        <Router>
            <div className="container">
                <header className="header">
                    <nav>
                        <Link to="/chat">Chat</Link>
                        <Link to="/login">Login</Link>
                        <Link to="/register">Register</Link>
                    </nav>
                </header>

                <Routes>
                    <Route path="/" element={<LandingPage />}/>
                    <Route path="/login" element={<Login />} />
                    <Route path="/chat" element={<Chat />} />
                    <Route path="/register" element={<Registration />} />
                </Routes>

                <footer className="footer">
                </footer>
            </div>
        </Router>
    );
}

export default App;





