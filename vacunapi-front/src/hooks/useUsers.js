import { useReducer } from "react"
import { authReducer, initialStateUser } from "../reducers/userReducer"
import { loginUser } from "../services/userServices";

export const useLogin = () => {

    const [state, dispatch] = useReducer(authReducer, initialStateUser);

    const login = async (data) => {

        dispatch({ type: 'LOGIN_INIT' });
        try {
            const userData = await loginUser(data);
            dispatch({ type: 'LOGIN_SUCCESS', payload: userData });
            localStorage.setItem('token', JSON.stringify(userData.token))
            localStorage.setItem('user', JSON.stringify(userData));
            return true;
        } catch (error) {
            dispatch({ type: 'LOGIN_ERROR', payload: error.message });
            return false;
        }
    };

    return {
        ...state,
        login
    };
};