import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import TextField from '@mui/material/TextField';
import logo from '../assets/img/logo.png'
import '../styles/login.css'
import { Context } from "../store/appContext.jsx";


export const LoginForm = () => {

    const { actions } = useContext(Context);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();

        const userLogin = {
            email,
            password
        };
        actions.login(userLogin);
        navigate('/dashboard')
    }

    return (
        <div className="container">
            <form className="form_container" onSubmit={handleSubmit}>
                <div className="logo_container">
                    <img src={logo} alt="Logo" width={300} />
                </div>
                <div className="title_container">
                    <p className="title">Inicia con tu cuenta</p>
                    <span className="subtitle">Comienza con nuestra app para tener un mayor control de tus resultados y pruebas</span>
                </div>
                <br />

                <div className="input_container">
                    <label className="input_label" htmlFor="email_field">Email</label>
                    <TextField
                        id="email_field"
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="Correo"
                        variant="standard"
                        required
                        fullWidth
                    />
                </div>

                <div className="input_container">
                    <label className="input_label" htmlFor="password_field">Password</label>
                    <TextField
                        id="password_field"
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Contraseña"
                        variant="standard"
                        required
                        fullWidth
                    />
                </div>

                <button
                    title="Sign In"
                    type="submit"
                    className="sign-in_btn btn btn-primary"
                >
                Iniciar Sesión
                </button>

                <div className="separator">
                    <hr className="line" />
                    <span>Or</span>
                    <hr className="line" />
                </div>

                <button title="Sign In with Google" type="button" className="sign-in_ggl">
                    <span>Sign In with Google</span>
                </button>

                <p className="note">Terms of use &amp; Conditions</p>
            </form>
        </div>
    );
}