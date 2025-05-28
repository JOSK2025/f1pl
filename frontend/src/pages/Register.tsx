import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const Register = () => {
    const [formData, setFormData] = useState({
        username: "",
        email: "",
        password: ""
    });
    const navigate = useNavigate();

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const response = await axios.post("http://localhost:8080/api/auth/register", formData);
            alert(response.data);
            navigate("/login");
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <div className="h-full flex flex-1 justify-center items-center p-4">
            <form className="p-6 w-full max-w-xl flex flex-col items-center gap-4 bg-stone-50 rounded-lg" onSubmit={handleSubmit}>
                <h2 className="text-2xl font-semibold text-stone-950 mb-4">Rejestracja</h2>
                <input
                    className="w-full p-2 border border-gray-300 rounded-md focus:border-stone-800 focus:outline-none text-stone-950"
                    type="text"
                    name="username"
                    placeholder="Nazwa użytkownika"
                    value={formData.username}
                    onChange={handleChange}
                />
                <input
                    className="w-full p-2 border border-gray-300 rounded-md focus:border-stone-800 focus:outline-none text-stone-950"
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                />
                <input
                    className="w-full p-2 border border-gray-300 rounded-md focus:border-stone-800 focus:outline-none text-stone-950"
                    type="password"
                    name="password"
                    placeholder="Hasło"
                    value={formData.password}
                    onChange={handleChange}
                />
                <button type="submit" className="cursor-pointer bg-red-800 text-stone-50 self-end p-2 rounded-md hover:bg-red-700 transition-colors">Zarejestruj</button>
            </form>
        </div>
    );
};

export default Register;