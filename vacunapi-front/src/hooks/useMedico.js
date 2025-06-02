import { useReducer } from "react"
import { initialStateCitas, listCita } from "../reducers/medicoReducer"
import { listCitas } from "../services/medicoServices";


export const useListCitasByMedico = () => {

    const [ state, dispatch ] = useReducer(listCita, initialStateCitas);

    const listaCitas = async () => {

        dispatch({ type: 'LIST_INIT' });
        try {
            const data = await listCitas();            
            dispatch({ type: 'LIST_SUCCESS', payload: data});
            return true;
        } catch (e) {
            dispatch({ type: 'LIST_ERROR', payload: e.message});
            return false;
        }
    }

    return { ...state, listaCitas };
}