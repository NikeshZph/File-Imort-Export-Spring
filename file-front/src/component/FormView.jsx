import React, { useState, useEffect } from 'react';
import axios from 'axios';
import axiosInstance from '../axiosInstance';

const ViewFormData = () => {
  const [formData, setFormData] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await axiosInstance.get('/form/getall');
      setFormData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const downloadExcel = async () => {
    try {
      const response = await axiosInstance.get('/form/excel', { responseType: 'blob' });
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', 'formData.xlsx');
      document.body.appendChild(link);
      link.click();
    } catch (error) {
      console.error('Error downloading Excel file:', error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Form Data</h2>
      <div className="mb-3">
        <button className="btn btn-primary" onClick={downloadExcel}>
          Download Excel
        </button>
      </div>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Last Name</th>
            <th>First Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>Gender</th>
            <th>Age Group</th>
            <th>Date of Birth</th>
            <th>Country</th>
            <th>Submit Time</th>
            <th>Submitted By</th>
          </tr>
        </thead>
        <tbody>
          {formData.map((data) => (
            <tr key={data.id}>
              <td>{data.id}</td>
              <td>{data.lname}</td>
              <td>{data.fname}</td>
              <td>{data.email}</td>
              <td>{data.address}</td>
              <td>{data.gender}</td>
              <td>{data.agegroup}</td>
              <td>{data.dob}</td>
              <td>{data.country}</td>
              <td>{new Date(data.submittime).toLocaleString()}</td>
              <td>{data.submittedby}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ViewFormData;
