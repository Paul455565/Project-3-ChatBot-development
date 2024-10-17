import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Chat from './components/Chat';
import LandingPage from './components/LandingPage.jsx';
import Login from './components/LoginPage';
import Registration from './components/RegistrationPage';
import PreviousChats from './components/PreviousChats';
import './App.css';

function App() {
    return (
        <Router>
            <div className="container">
                {/* Header with navigation */}
                <header className="header">
                    <nav>
                        <Link to="/">Home</Link>
                        <Link to="/chat">Chat</Link>
                        <Link to="/previous-chats">Previous Chats</Link>
                        <Link to="/login">Login</Link>
                        <Link to="/register">Register</Link>
                    </nav>
                </header>

                {/* Main content */}
                <main>
                    <Routes>
                        <Route path="/" element={<LandingPage />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/chat" element={<Chat />} />
                        <Route path="/previous-chats" element={<PreviousChats />} />
                        <Route path="/register" element={<Registration />} />
                    </Routes>
                </main>

                {/* Footer */}
                <footer className="footer">
                    <p>&copy; 2024 Chat App. All Rights Reserved.</p>
                </footer>
            </div>
        </Router>
    );
}

export default App;
