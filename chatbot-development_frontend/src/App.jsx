import Chat from './components/Chat';
import LandingPage from './components/LandingPage.jsx';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'; // Ensure react-router-dom is imported
import Registration from './components/RegistrationPage';
import PreviousChats from './components/PreviousChats';
import LoginPage from './components/LoginPage.jsx'; // Assuming you have this component
import './App.css';

function App() {
    return (
        <Router>
            <div>
                <Routes>
                    <Route path="/" element={<LandingPage />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/chat" element={<Chat />} />
                    <Route path="/previous-chats" element={<PreviousChats />} />
                    <Route path="/register" element={<Registration />} />
                </Routes>

                <footer className="footer">
                    <p>&copy; 2024 Chat App. All Rights Reserved.</p>
                </footer>
            </div>
        </Router>
    );
}

export default App;

