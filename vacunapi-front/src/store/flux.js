// eslint-disable-next-line no-unused-vars
const getState = ({ getStore, getActions, setStore }) => {
	const url = "http://localhost:8080";


	return {
		store: {
			message: null,
			isLogged: false,
			isAdmin: false,
			user: {},
			isLoadingUser: true,
			alert: {text: '', visible: false, background: 'primary'},
		},
		actions: {
			login: async (userLogin) => {
				const response = await fetch (`${url}/auth/login`,
					{
						method: 'POST',
						headers: { 'Content-Type': 'application/json' },
						body: JSON.stringify(userLogin)
					});

				if (!response.ok) {
					console.log("Error for login", response.status, response.statusText);
					setStore({
						alert: { visible: true, text: "Error for login!", background: "danger" }
					});
					setTimeout(() => {
						setStore({ alert: { visible: false, text: "", background: "" } });
					}, 2000);
				}

				const data = await response.json();
				setStore({
					isLogged: true,
					user: data,
					alert: { visible: true, text: "Login successful", background: "success" }
				})
				setTimeout(() => {
					setStore({ alert: { visible: false, text: "", background: "" } });
				}, 2000);

				localStorage.setItem('token', data.token);
				localStorage.setItem('user', JSON.stringify(data))
			}
		}
	};
};

export default getState;
