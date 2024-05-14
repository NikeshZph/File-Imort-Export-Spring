import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './component/Navbar';
import HomePage from './component/HomePage'; 
import UploadFile from './component/Upload';
import Download from './component/Import';
import View from './component/View';
import Form from './component/Form';
import SocialLogin from './component/Login';
import FormDataPage from './component/FormView';
import Pagination from './component/ViewPage.jsx';

function App() {
  return (
    <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<HomePage/>} />
          <Route path="/view" element={<View />} />
          <Route path="/upload" element={<UploadFile />} />
          <Route path="/import" element={<Download />} />
          <Route path = "/form" element = {<Form />} />
          <Route path = "/login" element = {<SocialLogin />} />
          <Route path = "/formview" element = {<FormDataPage />} />
          <Route path = "/page" element = {<Pagination />} />


        </Routes>
    </BrowserRouter>
  );
}

export default App;
