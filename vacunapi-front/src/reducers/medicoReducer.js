export const initialStateCitas = {
    loading: false,
    error: null,
    data: []
}

export const listCita = (state, action) => {
    switch (action.type) {

        case 'LIST_INIT':
            return { ...state, loading: true, error: null};
        case 'LIST_SUCCESS': 
            return { ...state, loading: false, data: action.payload};
        case 'LIST_ERROR':
            return { ...state, loading: false, error: action.payload};
    }
}