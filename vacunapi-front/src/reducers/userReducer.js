export const initialStateUser = {
    user: JSON.parse(localStorage.getItem('user')) || null,
    error: null,
    successMessage: null,
    loading: false
}

export const initialStateProfile = {
    loading: false,
    error: null,
    data: {}
}

export const authReducer = (state, action) => {
  switch (action.type) {

    //LOGIN
    case 'LOGIN_INIT':
        return { ...state, loading: true, error: null };
    case 'LOGIN_SUCCESS':
        return { ...state, loading: false, user: action.payload };
    case 'LOGIN_ERROR':
        return { ...state, loading: false, error: action.payload };

    default:
        return state;

  }  
};

export const profile = (state, action) => {
    switch (action.type) {

        case 'PROFILE_INIT':
            return { ...state, loading: true, error: null};
        case 'PROFILE_SUCCESS': 
            return { ...state, loading: false, data: action.payload};
        case 'PROFILE_ERROR':
            return { ...state, loading: false, error: action.payload};
    }
}