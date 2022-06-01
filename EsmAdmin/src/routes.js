import React from 'react'

const Dashboard = React.lazy(() => import('./views/dashboard/Dashboard'))

const Category = React.lazy(() => import('./views/stdio-esm/skill/Category'))
const Level = React.lazy(() => import('./views/stdio-esm/skill/Level'))
//skill
const AllSkills = React.lazy(() => import('./views/stdio-esm/skill/AllSkills'))
const AddSkill = React.lazy(() => import('./views/stdio-esm/skill/AddSkill'))
const EditSkill = React.lazy(() => import('./views/stdio-esm/skill/EditSkill'))
const DeleteSkill = React.lazy(() => import('./views/stdio-esm/skill/DeleteSkill'))
//project
const AllProjects = React.lazy(() => import('./views/stdio-esm/project/AllProjects'))
const AddProject = React.lazy(() => import('./views/stdio-esm/project/AddProject'))
const DeleteProject = React.lazy(() => import('./views/stdio-esm/project/DeleteProject'))
const EditProject = React.lazy(() => import('./views/stdio-esm/project/EditProject'))
//user
const AllUsers = React.lazy(() => import('./views/stdio-esm/user/AllUsers'))
const AddUsers = React.lazy(() => import('./views/stdio-esm/user/AddUser'))
const EditUser = React.lazy(() => import('./views/stdio-esm/user/EditUser'))
const DeleteUser = React.lazy(() => import('./views/stdio-esm/user/DeleteUser'))
//export CV
const ExportCV = React.lazy(() => import('./views/stdio-esm/export-cv/ExportCV'))
const UserDetails = React.lazy(() => import('./views/stdio-esm/user/UserProfile'))

const routes = [
  { path: '/', exact: false, name: 'Home' },
  { path: '/dashboard', name: 'Dashboard', element: Dashboard },

  { path: '/category', name: 'Category', element: Category },
  { path: '/level', name: 'Level', element: Level },
  { path: '/all-skills', name: 'All Skills', element: AllSkills },
  { path: '/skill/add-skill', name: 'Add Skill', element: AddSkill },
  { path: '/skill/edit-skill', name: 'Edit Skill', element: EditSkill },
  { path: '/skill/delete-skill', name: 'Delete Skill', element: DeleteSkill },
  { path: '/all-projects', name: 'All Project', element: AllProjects },
  { path: '/project/add-project', name: 'Add Project', element: AddProject },
  { path: '/project/delete-project', name: 'Delete Project', element: DeleteProject },
  { path: '/project/edit-project', name: 'Edit Project', element: EditProject },
  { path: '/all-users', name: 'All Users', element: AllUsers },
  { path: '/user/add-user', name: 'Add User', element: AddUsers },
  { path: '/user/delete-user', name: 'Delete User', element: DeleteUser },
  { path: '/user/edit-user', name: 'Edit User', element: EditUser },
  { path: '/export-cv', name: 'Export CV', element: ExportCV },
  { path: '/user/user-details', name: 'User Details', element: UserDetails },
]

export default routes
