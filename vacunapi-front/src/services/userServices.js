const base_url = 'http://localhost:8080';

export const loginUser = async (userData) => {
    const response = await fetch(`${base_url}/auth/login`,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData)
        });

    if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Error al iniciar sesión');
    }

    return response.json()


} 