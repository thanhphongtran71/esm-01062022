import React, { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import {
  CButton,
  CCard,
  CCardBody,
  CCardGroup,
  CCol,
  CContainer,
  CForm,
  CFormInput,
  CInputGroup,
  CInputGroupText,
  CRow,
  CImage,
} from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilLockLocked, cilUser } from '@coreui/icons'
import axios from 'axios'
import logo from 'src/assets/brand/logo.svg'

const Login = () => {
  const [userName, setUserName] = useState('')
  const [passWord, setPassWord] = useState('')
  const [error, setError] = useState('')
  const navigate = useNavigate()

  const handleUserNameOnChange = (event) => {
    setUserName({
      userName: event.target.value,
    })
  }

  const handlePassWorkOnChange = (event) => {
    setPassWord({
      passWord: event.target.value,
    })
  }
  const handleLogin = (event) => {
    event.preventDefault()
    axios
      .post('public/login', null, {
        params: {
          username: userName.userName,
          password: passWord.passWord,
        },
      })
      .then((res) => {
        if (res.data.code !== 400) {
          localStorage.setItem('token', res.data.accessToken)
          localStorage.setItem('refreshToken', res.data.refreshToken)
          localStorage.setItem('userId', res.data.id)
          navigate('/dashboard', { state: { user: userName } })
        } else {
          setError(res.data.message)
        }
      })
      .catch((err) => {
        setError('Incorrect password !')
        console.log(err)
      })
  }

  return (
    <div className="bg-light min-vh-100 d-flex flex-row align-items-center">
      <CContainer>
        <CRow className="justify-content-center">
          <CCol md={8}>
            <CCardGroup>
              <CCard className="p-4">
                <CCardBody>
                  <CForm>
                    <h1>Login</h1>
                    <p className="text-medium-emphasis">Sign In to your account</p>
                    <CInputGroup className="mb-3">
                      <CInputGroupText>
                        <CIcon icon={cilUser} />
                      </CInputGroupText>
                      <CFormInput
                        onChange={handleUserNameOnChange}
                        placeholder="Username"
                        autoComplete="username"
                      />
                    </CInputGroup>
                    <CInputGroup className="mb-4">
                      <CInputGroupText>
                        <CIcon icon={cilLockLocked} />
                      </CInputGroupText>
                      <CFormInput
                        onChange={handlePassWorkOnChange}
                        type="password"
                        placeholder="Password"
                        autoComplete="current-password"
                      />
                    </CInputGroup>
                    {error && <p style={{ color: 'red' }}>{error}</p>}
                    <CRow>
                      <CCol xs={6}>
                        <CButton onClick={handleLogin} color="dark" className="px-4">
                          Login
                        </CButton>
                      </CCol>
                      <CCol xs={6} className="text-right">
                        <CButton color="link" className="px-0">
                          <Link to="/reset-password"> Reset password?</Link>
                        </CButton>
                      </CCol>
                    </CRow>
                  </CForm>
                </CCardBody>
              </CCard>
              <CCard className="text-white bg-dark py-5" style={{ width: '44%' }}>
                <CCardBody className="text-center">
                  <CImage rounded src={logo} width={240} height={200} />

                  {/* <Link to="/register">
                    <CButton color="dark" className="mt-3" active tabIndex={-1}>
                      Register Now!
                    </CButton>
                  </Link> */}
                </CCardBody>
              </CCard>
            </CCardGroup>
          </CCol>
        </CRow>
      </CContainer>
    </div>
  )
}

export default Login
