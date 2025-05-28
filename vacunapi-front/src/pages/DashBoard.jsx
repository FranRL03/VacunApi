import React from "react";
import '../styles/dashboard.css';
import { Container, Row, Col, Card, Button, Table } from 'react-bootstrap';


export const Dashboard = () => {

  const citasHoy = [
    { hora: "9:00 AM", paciente: "Jane Doe" },
    { hora: "10:00 AM", paciente: "John Doe" },
    { hora: "11:30 AM", paciente: "Alice Johnson" },
    { hora: "2:00 PM", paciente: "Bob Brown" },
  ];

  return (
    <div className="d-flex">
      {/* Sidebar */}
      <div className="bg-primary text-white p-3 vh-100" style={{ width: '250px' }}>
        <ul className="nav flex-column">
          <li className="nav-item">
            <a className="nav-link text-white active" href="#">Home</a>
          </li>
          <li className="nav-item">
            <a className="nav-link text-white" href="#">Features</a>
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
        <h2 className="mb-4">Bienvenido, Dr. Pérez</h2>

        <Row className="mb-4">
          <Col md={4}>
            <Card>
              <Card.Body>
                <Card.Title>Próxima cita</Card.Title>
                <Card.Text>10:00 AM con John Doe</Card.Text>
              </Card.Body>
            </Card>
          </Col>

          <Col md={4}>
            <Card>
              <Card.Body>
                <Card.Title>Pacientes de hoy</Card.Title>
                <Card.Text>5</Card.Text>
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
                {citasHoy.map((cita, index) => (
                  <tr key={index}>
                    <td>{cita.hora}</td>
                    <td>{cita.paciente}</td>
                    <td><Button variant="outline-primary" size="sm">Ver</Button></td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </Card.Body>
        </Card>
      </div>
    </div>

  )
}