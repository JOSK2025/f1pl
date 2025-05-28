import {Route, BrowserRouter as Router, Routes} from "react-router-dom";
import Home from "./pages/Home.tsx";
import Login from "./pages/Login.tsx";
import Register from "./pages/Register.tsx";
import Navbar from "./components/Navbar.tsx";
import Articles from "./pages/Articles.tsx";

function App() {
    return (
        <div className="flex flex-col min-h-screen bg-stone-100">
            <Router>
                <Navbar />
                <main className="flex justify-center items-center flex-1 overflow-y-auto">
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/register" element={<Register />} />
                        <Route path="/articles" element={<Articles />} />
                    </Routes>
                </main>
            </Router>
        </div>
  )
}

export default App
