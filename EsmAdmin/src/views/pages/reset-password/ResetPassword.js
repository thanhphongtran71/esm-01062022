import React, { useState } from 'react'
import {
  CButton,
  CCard,
  CCardBody,
  CCol,
  CContainer,
  CForm,
  CFormInput,
  CInputGroup,
  CInputGroupText,
  CRow,
  CSpinner,
  CImage,
} from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilUser } from '@coreui/icons'
import axios from 'axios'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'
import logo from 'src/assets/brand/logo.svg'
import './ResetPassword.scss'

const ResetPassword = () => {
  const [userName, setUserName] = useState('')
  const [msgError, setMsgError] = useState('')
  const [spinnerLoading, setSpinnerLoading] = useState(false)
  const navigate = useNavigate()

  const handleUserNameOnChange = (event) => {
    setUserName(event.target.value)
    if (userName) setMsgError('')
  }
  const handleSubmitOnClick = (e) => {
    setSpinnerLoading(true)
    e.preventDefault()
    if (userName) {
      axios
        .post(`users/reset-password`, null, {
          params: {
            username: userName,
          },
        })
        .then((res) => {
          if (res.data.code === 400) {
            setMsgError(res.data.message)
            setSpinnerLoading(false)
          } else {
            const partialEmail = userName.replace(/(\w{5})[\w.-]+@([\w.]+\w)/, '$1***@$2')
            toast.success(`Reset password successfully, please check the email ${partialEmail}!`)
            navigate('login')
          }
        })
        .catch((err) => {
          console.log(err)
        })
    } else {
      setSpinnerLoading(false)
      setMsgError('Please enter your username !')
    }
  }
  return (
    <div className="bg-light min-vh-100 d-flex flex-row align-items-center">
      <CContainer>
        <CRow className="justify-content-center">
          <CCol md={9} lg={7} xl={6}>
            <CCard className="mx-4">
              <CCardBody className="p-4">
                <CForm>
                  <div className="logo-reset">
                    <img src={logo} alt="Stdio Logo"></img>
                    <p className="text-medium-emphasis">
                      <b>Reset password</b>
                    </p>
                  </div>
                  {/* <p className="text-medium-emphasis">Enter your username</p> */}
                  <CInputGroup className="mb-3">
                    <CInputGroupText>
                      <CIcon icon={cilUser} />
                    </CInputGroupText>
                    <CFormInput
                      value={userName}
                      onChange={handleUserNameOnChange}
                      //   placeholder="Username"
                      autoComplete="username"
                      placeholder="Enter your username"
                    />
                  </CInputGroup>
                  {msgError && <p style={{ color: 'red' }}>{msgError}</p>}
                  <div className="button-group">
                    <CButton
                      color="secondary"
                      className="me-2 back-button"
                      onClick={() => navigate('/login')}
                    >
                      Cancel
                    </CButton>
                    <CButton color="dark" onClick={handleSubmitOnClick}>
                      {spinnerLoading === true && (
                        <CSpinner component="span" size="sm" aria-hidden="true" />
                      )}
                      &nbsp;Submit
                    </CButton>
                  </div>
                </CForm>
              </CCardBody>
            </CCard>
          </CCol>
        </CRow>
      </CContainer>
    </div>
  )
}

export default ResetPassword
