import React, { useContext } from "react";
import '../styles/dashboard.css';
import { Row, Col, Card, Button, Table } from 'react-bootstrap';
import { Context } from "../store/appContext";

export const Dashboard = () => {

    const { store } = useContext(Context);
    const user = store.user;

    console.log(user.username);
    

  return (
    <div className="d-flex">
      {/* Sidebar */}
      <div className="bg-primary text-white p-3 vh-100" style={{ width: '250px' }}>
        <ul className="nav flex-column">
          <li className="nav-item">
            <a className="nav-link text-white active" href="#">Home</a>
          </li>
          <li className="nav-item">
            <a className="nav-link text-white" href="#">Calendario</a>
          </li>
          <li className="nav-item">
            <a className="nav-link text-white" href="#">Pricing</a>
          </li>
          <li className="nav-item">
            <a className="nav-link text-white" href="#">About</a>
          </li>
          <li className="nav-item dropdown">
            <a className="nav-link dropdown-toggle text-white" data-bs-toggle="dropdown" href="#" role="button">Configuración</a>
            <ul className="dropdown-menu">
              <li><a className="dropdown-item" href="#">Perfil</a></li>
              <li><a className="dropdown-item" href="#">Editar el perfil</a></li>
              <li><a className="dropdown-item" href="#">Cambiar contraseña</a></li>
              <li><hr className="dropdown-divider" /></li>
              <li><a className="dropdown-item text-danger" href="#">Cerrar sesión</a></li>
            </ul>
          </li>
        </ul>
      </div>

      {/* Main Content */}
      <div className="p-4 flex-grow-1">
        <h2 className="mb-4">Bienvenido, {user.username}</h2>

        <Row className="mb-4">
          <Col md={4}>
            <Card>
              <Card.Body>
                <Card.Title>Próxima cita</Card.Title>
                <Card.Text>
                  {
                    store.listCitas.length === 0 ? "No hay pacientes para hoy" : `${store.listCitas[0].hora} con ${store.listCitas[0].paciente}`
                  }
                </Card.Text>

              </Card.Body>
            </Card>
          </Col>

          <Col md={4}>
            <Card>
              <Card.Body>
                <Card.Title>Pacientes de hoy</Card.Title>
                <Card.Text>
                  {store.listCitas.length === 0 ? 0 : store.listCitas.length}
                </Card.Text>
              </Card.Body>
            </Card>
          </Col>

          <Col md={4}>
            <Card>
              <Card.Body>
                <Card.Title>Acciones rápidas</Card.Title>
                <Button variant="primary">Crear receta</Button>
                <Button variant="secondary" className="ms-3">Ver historial</Button>
              </Card.Body>
            </Card>
          </Col>
        </Row>

        <Card className="mb-4">
          <Card.Body>
            <Card.Title>Citas de hoy</Card.Title>
              <Table striped bordered hover>
                <thead>
                  <tr>
                    <th>Hora</th>
                    <th>Paciente</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {store.listCitas.length === 0 ? (
                    <tr><td colSpan="3">No hay citas para hoy</td></tr>
                  ) : (
                    store.listCitas.map((cita) => (
                      <tr key={cita.id}>
                        <td>{cita.hora}</td>
                        <td>{cita.paciente}</td>
                        <td>
                          <Button variant="outline-primary" size="sm">Ver</Button>
                        </td>
                      </tr>
                    ))
                  )}
                </tbody>
              </Table>
          </Card.Body>
        </Card>
      </div>
    </div>

  )
}