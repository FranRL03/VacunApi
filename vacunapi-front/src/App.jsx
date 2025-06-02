import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import { LoginForm } from './pages/LoginForm'
import { Dashboard } from './pages/DashBoard'
// eslint-disable-next-line no-unused-vars
import injectContext from './store/appContext.jsx'
import { Error } from './components/Error.jsx'

// const App = () => {
//   return (
//     <BrowserRouter>
//       <Routes>
//         <Route path="/" element={<LoginForm />} />
//         <Route path="/dashboard" element={<Dashboard />} />
//       </Routes>
//     </BrowserRouter>
//   )
// }

// export default injectContext(App);

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginForm />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route element={<Error />} path='*' />
      </Routes>
    </BrowserRouter>
  )
}

export default App;
