import React, { useState, useEffect } from 'react'
import { CAvatar, CCardTitle, CCard, CCardBody, CCardText } from '@coreui/react'
import CIcon from '@coreui/icons-react'
import {
  cilEnvelopeOpen,
  cilCalendar,
  cilLocationPin,
  cilPhone,
  cilUser,
  cilGlobeAlt,
} from '@coreui/icons'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'

const UserProfile = () => {
  const [employee, setEmployee] = useState({})
  const navigate = useNavigate()
  useEffect(() => {
    const config = {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
      },
    }
    // const userId = localStorage.getItem('userId')
    axios
      .get(`users/user-details`, config)
      .then((res) => {
        const result = res.data
        setEmployee({ ...result })
        console.log('res ' + JSON.stringify(res))
        console.log('result ' + JSON.stringify(result))
        console.log('data ' + JSON.stringify(employee))
      })
      .catch((err) => {
        navigate('/login')
      })
  }, [])

  return (
    <>
      <div className="bg-white">
        <div className="p-3 mb-2 text-info rounded-pill">
          <div className="d-flex justify-content-center w-100 p-3">
            <CAvatar src={employee.avatar_img} size="xl" />
          </div>
          <div className="w-100 d-flex justify-content-center">
            <CCardTitle className="fs-2">{employee.name}</CCardTitle>
          </div>
          <div className="w-100 d-flex justify-content-center">
            <CCardTitle className="fs-4">{employee.job_title}</CCardTitle>
          </div>
        </div>
        <div className="w-50 d-inline-block border rounded" style={{ height: '140px' }}>
          <div className="w-50 d-inline-block">
            <div>
              <CIcon icon={cilCalendar} size="lg" />
              <span className="p-3">{employee.date_of_birth}</span>
            </div>
            <div>
              <CIcon icon={cilEnvelopeOpen} size="lg" />
              <span className="p-3">{employee.email}</span>
            </div>
            <div>
              <CIcon icon={cilLocationPin} size="lg" />
              <span className="p-3">{employee.address}</span>
            </div>
          </div>
          <div className="w-50 d-inline-block">
            <div>
              <CIcon icon={cilPhone} size="lg" />
              <span className="p-3">{employee.phone_number}</span>
            </div>
            <div>
              <CIcon icon={cilGlobeAlt} size="lg" />
              <span className="p-3">{employee.website}</span>
            </div>
            <div>
              <CIcon icon={cilUser} size="lg" />
              <span className="p-3">{employee.gender}</span>
            </div>
          </div>
        </div>
        <div className="w-50 d-inline-block">
          <CCard style={{ top: '-30px' }}>
            <CCardBody>
              <CCardTitle className="text-uppercase d-flex justify-content-center">
                Professional Summary
              </CCardTitle>
              <CCardText>{employee.professional_summary}</CCardText>
            </CCardBody>
          </CCard>
        </div>
      </div>
      <div className="w-100">
        <CCard style={{ top: '-30px' }}>
          <CCardBody>
            <CCardTitle className="text-uppercase d-flex justify-content-center">
              Employment History
            </CCardTitle>
          </CCardBody>
        </CCard>
        <div className="w-50 d-inline-block border rounded">
          <CCard style={{ top: '-30px' }}>
            <CCardBody>
              {/* <CCardTitle className="text-uppercase d-flex justify-content-center">
                Job Title: {employee.job_title_history}
              </CCardTitle>
              <CCardText className="text-capitalize d-inline-block p-3">
                Employer: {employee.employer_history}
              </CCardText>
              <CCardText className="text-capitalize d-inline-block p-3">
                Description: {employee.description_history}
              </CCardText>
              <CCardText className="text-capitalize d-inline-block p-3">
                Start Date: {employee.start_date_history}
              </CCardText>
              <CCardText className="text-capitalize d-inline-block p-3">
                End Date: {employee.end_date_history}
              </CCardText> */}
            </CCardBody>
          </CCard>
        </div>
      </div>
    </>
  )
}

export default UserProfile
