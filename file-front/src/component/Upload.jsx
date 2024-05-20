import React, { useState } from 'react';
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import axios from 'axios';
import axiosInstance from '../axiosInstance';

const UploadedFile = () => {
  const [file, setFile] = useState(null);

  const handleFileChange = (event) => {
    const uploadedFile = event.target.files[0];
    setFile(uploadedFile);
  };

  const uploadFile = () => {
    if (!file) {
      toast.error('Please select a file to upload', {
        position: "bottom-left"
      });
      return;
    }
  
    const formData = new FormData();
    formData.append('file', file);
  
    axios.post('http://localhost:8080/api/excel/upload', formData)
      .then(response => {
        console.log('File uploaded successfully:', response.data);
        toast.success(response.data.message, {
          position: "bottom-left"
        });
      })
      .catch(error => {
        console.log('Response from backend:', error.response.data);
        toast.error(error.response.data.message, {
          position: "bottom-left"
        });
      });
  };
  

  const downloadSample = () => {
    axiosInstance.get('api/excel/sample', { responseType: 'blob' })
      .then(response => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'sample.xlsx');
        document.body.appendChild(link);
        link.click();
        window.URL.revokeObjectURL(url);
      })
        .catch(error => {
          console.error('Error uploading file:', error);
          toast.error('Error uploading file', {
            position: "bottom-left"
          });
        });
    };

  return (
    <div className="container mt-5 ">
      <ToastContainer />
      <h2>Upload Excel File</h2>
      <div className="input-group mb-3">
        <input
          type="file"
          className="form-control"
          onChange={handleFileChange}
        />
      </div>
      <button
        className="btn btn-primary"
        onClick={uploadFile}
        disabled={!file}
      >
        Upload
      </button>
      <button
        className="btn btn-secondary mx-3"
        onClick={downloadSample}
      >
        Download Sample
      </button>
    </div>
  );
};

export default UploadedFile;
