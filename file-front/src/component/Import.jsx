import React, { useState } from 'react';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import axiosInstance from '../axiosInstance';

const Download = () => {
  const [loading, setLoading] = useState(false);

  const handleDownload = async () => {
    try {
      setLoading(true);
      const response = await axiosInstance.get('/api/excel/download', {
        responseType: 'blob',
      });
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', 'file.xlsx');
      document.body.appendChild(link);
      link.click();
      setLoading(false);
      toast.success('Download successful');
    } catch (error) {
      setLoading(false);
      toast.error('Download failed');
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center mt-5">
        <div className="col-md-6">
          <button
            className="btn btn-primary btn-block"
            onClick={handleDownload}
            disabled={loading}
          >
            {loading ? 'Downloading...' : 'Download'}
          </button>
          <ToastContainer />
        </div>
      </div>
    </div>
  );
};

export default Download;
