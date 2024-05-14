import React, { useState, useEffect } from 'react';
import axios from 'axios';
import axiosInstance from '../axiosInstance';
import { Table, Button } from 'react-bootstrap';

const Pagination = () => {
  const [employees, setEmployees] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);
  const [sortBy, setSortBy] = useState(null);

  useEffect(() => {
    fetchEmployees();
  }, [currentPage, pageSize, sortBy]);

  const fetchEmployees = async () => {
    try {
      const response = await axiosInstance.get(`api/excel/pagination/${currentPage}/${pageSize}/${sortBy || 'id'}`);
      setEmployees(response.data.content);
      setTotalPages(response.data.totalPages);
    } catch (error) {
      console.error('Error fetching employees:', error);
    }
  };


  const handlePageSizeChange = (event) => {
    setPageSize(event.target.value);
    setCurrentPage(1);
  };

  const handleSortChange = (field) => {
    setSortBy(field);
    setCurrentPage(1);
  };

  return (
    <div className="container">
      <div className="mb-3">
        <label htmlFor="pageSize" className="form-label">Page Size:</label>
        <select className="form-select" id="pageSize" value={pageSize} onChange={handlePageSizeChange}>
          <option value="10">10</option>
          <option value="20">20</option>
          <option value="50">50</option>
        </select>
      </div>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th onClick={() => handleSortChange('id')}>ID</th>
            <th onClick={() => handleSortChange('name')}>Name</th>
            <th onClick={() => handleSortChange('department')}>Department</th>
            <th onClick={() => handleSortChange('address')}>Address</th>
            <th onClick={() => handleSortChange('salary')}>Salary</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((employee, index) => (
            <tr key={employee.id}>
              <td>{(currentPage - 1) * pageSize + index + 1}</td>
              <td>{employee.name}</td>
              <td>{employee.department}</td>
              <td>{employee.address}</td>
              <td>{employee.salary}</td>
            </tr>
          ))}
        </tbody>
      </Table>
      <div className="d-flex justify-content-between align-items-center">
        <Button disabled={currentPage === 1} onClick={() => {
        if (currentPage>=0){
          setCurrentPage(currentPage => currentPage - 1)
        }
        }}>Previous</Button>
        <span>{`Page ${currentPage} of ${totalPages}`}</span>
        <Button disabled={currentPage === totalPages} onClick={() => setCurrentPage(currentPage => currentPage + 1)}>Next</Button>
      </div>
    </div>
  );
};

export default Pagination;