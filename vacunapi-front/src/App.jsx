import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import { LoginForm } from './pages/LoginForm'
import { Dashboard } from './pages/DashBoard'
import injectContext from './store/appContext.jsx'
import { Error } from './components/Error.jsx'
import ScrollToTop from './components/ScrollToTop.jsx'
import { Alert } from './components/Alert.jsx'

// eslint-disable-next-line react-refresh/only-export-components
const App = () => {
  return (
    <BrowserRouter>
      <ScrollToTop>
        {/* <Alert /> */}
        <Routes>
          <Route path="/" element={<LoginForm />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route element={<Error />} path='*' />
        </Routes>
      </ScrollToTop>
    </BrowserRouter>
  )
}

// eslint-disable-next-line react-refresh/only-export-components
export default injectContext(App);

// function App() {
//   return (
//     <BrowserRouter>
//       <Routes>
//         <Route path="/" element={<LoginForm />} />
//         <Route path="/dashboard" element={<Dashboard />} />
//         <Route element={<Error />} path='*' />
//       </Routes>
//     </BrowserRouter>
//   )
// }

// export default App;
