import {Route, BrowserRouter as Router, Routes} from "react-router-dom";
import Home from "./pages/Home.tsx";
import Login from "./pages/Login.tsx";
import Register from "./pages/Register.tsx";
import Navbar from "./components/Navbar.tsx";
import Articles from "./pages/Articles.tsx";

function App() {
    return (
        <div className="min-h-screen bg-stone-50">
            <Router>
                <Navbar />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/articles" element={<Articles />} />
                </Routes>
            </Router>
        </div>
  )
}

export default App
