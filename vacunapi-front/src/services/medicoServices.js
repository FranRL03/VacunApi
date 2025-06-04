import { base_url } from "./userServices"

export const listCitas = async () => {
    const token = localStorage.getItem('token')
    const response = await fetch(`${base_url}/medico/citas`,
        {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            },
        });

        if (!response) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Error al obtener la lista');
        }        
        return response.json()
}

export const medicoProfile = async () => {
    const token = localStorage.getItem('token');
    const response = await fetch(`${base_url}/medico/profile`, 
        {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

    if (!response) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Error al obtener los datos')
    }

    return response.json();
}