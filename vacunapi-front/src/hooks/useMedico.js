import { useReducer } from "react"
import { initialStateCitas, listCita } from "../reducers/medicoReducer"
import { listCitas, medicoProfile } from "../services/medicoServices";
import { initialStateProfile, profile } from "../reducers/userReducer";


export const useListCitasByMedico = () => {

    const [state, dispatch] = useReducer(listCita, initialStateCitas);

    const listaCitas = async () => {

        dispatch({ type: 'LIST_INIT' });
        try {
            const data = await listCitas();
            dispatch({ type: 'LIST_SUCCESS', payload: data });
            return true;
        } catch (e) {
            dispatch({ type: 'LIST_ERROR', payload: e.message });
            return false;
        }
    }

    return { ...state, listaCitas };
}

export const useMedicoProfile = () => {

    const [state, dispatch] = useReducer(profile, initialStateProfile);

    const getMedicoprofile = async () => {

        dispatch({ type: 'PROFILE_INIT' });
        try {
            const data = await medicoProfile();
            console.log(data);
            
            dispatch({ type: 'PROFILE_SUCCESS', payload: data });
            return true;
        } catch (e) {
            dispatch({ type: 'PROFILE_ERROR', payload: e.message });
            return false;
        }
    }

    return { ...state, getMedicoprofile };
}