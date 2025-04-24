import React, {useState} from "react";
import axios from "axios";

const Register = () => {
    const [formData, setFormData] = useState({
        email: "",
        password: ""
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const response = await axios.post("http://localhost:8080/api/auth/login", formData);
            alert(response.data);
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <div className="min-h-screen flex justify-center items-center">
            <form className="p-30 w-6/10 flex flex-col items-center gap-4" onSubmit={handleSubmit}>
                <h2 className="">Logowanie</h2>
                <input
                    className="w-full p-1 border-2 border-transparent rounded-sm bg-stone-200 focus:border-stone-800 focus:outline-none text-stone-950"
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                />
                <input
                    className="w-full p-1 border-2 border-transparent rounded-sm bg-stone-200 focus:border-stone-800 focus:outline-none text-stone-950"
                    type="password"
                    name="password"
                    placeholder="Hasło"
                    value={formData.password}
                    onChange={handleChange}
                />
                <button type="submit" className="cursor-pointer bg-red-800 text-stone-50 self-end p-1.5 rounded-sm">Zaloguj</button>
            </form>
        </div>
    );
};

export default Register;