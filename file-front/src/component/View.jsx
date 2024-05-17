import React, { useState, useEffect } from 'react';
import axiosInstance from '../axiosInstance';

const View = () => {
  const [employees, setEmployees] = useState([]);
  const [newEmployee, setNewEmployee] = useState({
    name: '',
    department: '',
    address: '',
    salary: '' 
  });
  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      let url = 'api/excel/employee';
      if (searchQuery) {
        url = `/api/excel/search?searchtext=${searchQuery}`;
      }
      const response = await axiosInstance.get(url);
      setEmployees(response.data);
    } catch (error) {
      console.error('Error fetching employees:', error);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewEmployee({ ...newEmployee, [name]: value });
  };

  const handleSearchChange = (e) => {
    setSearchQuery(e.target.value);
  };

  const handleAddEmployee = async () => {
    try {
      await axiosInstance.post('/api/excel/add', newEmployee);
      setNewEmployee({ name: '', department: '', address: '', salary: '' }); 
      fetchData();
    } catch (error) {
      console.error('Error adding employee:', error);
    }
  };

  const handleSearch = async () => {
    try {
      fetchData();
    } catch (error) {
      console.error('Error searching employees:', error);
    }
  };

  return (
    <div className="container">
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '20px' }}>
        <h1>Employee List</h1>
        <div>
          <input
            type="text"
            placeholder="Search"
            value={searchQuery}
            onChange={handleSearchChange}
          />
          <button className="btn btn-primary" onClick={handleSearch}>Search</button>
        </div>
      </div>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Department</th>
            <th>Address</th>
            <th>Salary</th> 
          </tr>
        </thead>
        <tbody>
          {employees.map(employee => (
            <tr key={employee.id}>
              <td>{employee.id}</td>
              <td>{employee.name}</td>
              <td>{employee.department}</td>
              <td>{employee.address}</td>
              <td>{employee.salary}</td> 
            </tr>
          ))}
        </tbody>
      </table>

      <h2>Add Employee</h2>
      <div>
        <input
          type="text"
          name="name"
          placeholder="Name"
          value={newEmployee.name}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="department"
          placeholder="Department"
          value={newEmployee.department}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="address"
          placeholder="Address"
          value={newEmployee.address}
          onChange={handleInputChange}
        />
        <input
          type="number" 
          name="salary"
          placeholder="Salary"
          value={newEmployee.salary}
          onChange={handleInputChange}
        />
        <button className="btn btn-primary" onClick={handleAddEmployee}>Add</button>
      </div>
    </div>
  );
};

export default View;
