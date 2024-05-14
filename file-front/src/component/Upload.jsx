import React, { useState } from 'react';
import { ToastContainer, toast } from "react-toastify";

import axios from 'axios';

const UploadedFile = () => {
  const [file, setFile] = useState(null);

  const handleFileChange = (event) => {
    const uploadedFile = event.target.files[0];
    setFile(uploadedFile);
  };

  const uploadFile = () => {
    const formData = new FormData();
    formData.append('file', file);
    
    axios.post('http://localhost:8080/api/excel/upload', formData)
      .then(response => {
        console.log('File uploaded successfully:', response.data);
        toast.success('File uploaded successfully', {
          position: "bottom-left"
        });
        
      })
      .catch(error => {
        console.error('Error uploading file:', error);
      });
  };

  return (
    <div className="container mt-5">
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
    </div>
  );
};

export default UploadedFile;
