import React from 'react'
import CIcon from '@coreui/icons-react'
import {
  cilContact,
  cilControl,
  cilCursor,
  cilDrop,
  cilFolder,
  cilHome,
  cilKeyboard,
  cilLevelUp,
  cilNewspaper,
  cilUser,
} from '@coreui/icons'
import { CNavGroup, CNavItem, CNavTitle } from '@coreui/react'

const _nav = [
  {
    component: CNavItem,
    name: 'Home',
    to: '/dashboard',
    icon: <CIcon icon={cilHome} customClassName="nav-icon" />,
    // badge: {
    //   color: 'info',
    //   text: 'NEW',
    // },
  },
  {
    component: CNavTitle,
    name: 'SKILL',
  },
  {
    component: CNavItem,
    name: 'Category',
    to: '/category',
    icon: <CIcon icon={cilNewspaper} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'Level',
    to: '/level',
    icon: <CIcon icon={cilLevelUp} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'All Skills',
    to: '/all-skills',
    icon: <CIcon icon={cilKeyboard} customClassName="nav-icon" />,
  },
  {
    component: CNavGroup,
    name: 'Manager Skills',
    to: '/skill',
    icon: <CIcon icon={cilControl} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: 'Add Skill',
        to: '/skill/add-skill',
      },
      {
        component: CNavItem,
        name: 'Edit Skill',
        to: '/skill/edit-skill',
      },
      {
        component: CNavItem,
        name: 'Delete Skill',
        to: '/skill/delete-skill',
      },
    ],
  },
  {
    component: CNavTitle,
    name: 'Projects',
  },
  {
    component: CNavItem,
    name: 'All Projects',
    to: '/all-projects',
    icon: <CIcon icon={cilDrop} customClassName="nav-icon" />,
  },
  {
    component: CNavGroup,
    name: 'Manager Projects',
    to: '/project',
    icon: <CIcon icon={cilCursor} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: 'Add Project',
        to: '/project/add-project',
      },
      {
        component: CNavItem,
        name: 'Edit Project',
        to: '/project/edit-project',
      },
      {
        component: CNavItem,
        name: 'Delete Project',
        to: '/project/delete-project',
      },
    ],
  },
  {
    component: CNavTitle,
    name: 'User',
  },
  {
    component: CNavItem,
    name: 'All Users',
    to: '/all-users',
    icon: <CIcon icon={cilUser} customClassName="nav-icon" />,
  },
  {
    component: CNavGroup,
    name: 'Manage Users',
    icon: <CIcon icon={cilContact} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: 'Add User',
        to: '/user/add-user',
      },
      {
        component: CNavItem,
        name: 'Edit User',
        to: '/user/edit-user',
      },
      {
        component: CNavItem,
        name: 'Delete User',
        to: '/user/delete-user',
      },
    ],
  },
  {
    component: CNavTitle,
    name: 'Extras',
  },
  {
    component: CNavItem,
    name: 'Export CV',
    to: '/export-cv',
    icon: <CIcon icon={cilFolder} customClassName="nav-icon" />,
  },
]

export default _nav
