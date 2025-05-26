import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useLogin } from "../hooks/useUsers.js"
import TextField from '@mui/material/TextField';
import logo from '../assets/img/logo.png'
import '../styles/login.css'


export const LoginForm = () => {

    const { loading, error, login } = useLogin();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        const success = await login({ email, password });

        if (success) {
            navigate('/dashboard');
        }
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
                        error={!!error}
                        helperText={error && "Error en el correo o contraseña"}
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
                        error={!!error}
                        helperText={error && "Error en el correo o contraseña"}
                        fullWidth
                    />
                </div>

                <button
                    title="Sign In"
                    type="submit"
                    className="sign-in_btn"
                    disabled={loading}
                >
                    <span>{loading ? 'Cargando...' : 'Entrar'}</span>
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