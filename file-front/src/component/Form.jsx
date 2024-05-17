import React, { useState } from 'react';
import {
  MDBBtn,
  MDBContainer,
  MDBCard,
  MDBCardBody,
  MDBInput,
  MDBCol,
  MDBRow
} from 'mdb-react-ui-kit';
import axiosInstance from '../axiosInstance';
import { ToastContainer, toast } from 'react-toastify';

function Form() {
  const [formData, setFormData] = useState({
    fname: '',
    lname: '',
    email: '',
    address: '',
    gender: '',
    ageGroup: '',
    country: '',
    dob: ''
  });

  const [submittedDate, setSubmittedDate] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formDataToSend = {
        lname: formData.lname,
        fname: formData.fname,
        email: formData.email,
        address: formData.address,
        gender: formData.gender,
        agegroup: formData.ageGroup,
        dob: formData.dob,
        country: formData.country,
        submittime: new Date(),
        submittedby: 'TODO: Get this from user session'
      };

      const jsessionId = localStorage.getItem('JSESSIONID');
      const headers = jsessionId ? { 'Cookie': jsessionId } : {};

      const response = await axiosInstance.post('/form/save', formDataToSend, { headers });
      console.log(response.data);
      toast.success(response.data.message)
      setSubmittedDate(new Date());
    } catch (error) {
      console.error('Error submitting form:', error);
    }
  };

  return (
    <MDBContainer fluid className='d-flex align-items-center justify-content-center bg-image' style={{backgroundImage: 'url(https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp)', minHeight: '100vh'}}>
      <div className='mask gradient-custom-3'></div>
      <MDBCard className='m-5' style={{width: '100%', maxWidth: '600px',backgroundColor:"#b9cabf"}}>
        <MDBCardBody className='px-5'>
          <h2 className="text-uppercase text-center mb-5">Please Fill The Form</h2>
          <form onSubmit={handleSubmit}>
            <MDBRow>
              <MDBCol size='6'>
              <MDBInput name='fname' wrapperClass='mb-4' label='First Name' size='lg' id='formfname' type='text' value={formData.fname} onChange={handleChange} required/>              </MDBCol>
              <MDBCol size='6'>
              <MDBInput name='lname' wrapperClass='mb-4' label='Last Name' size='lg' id='formlname' type='text' value={formData.lname} onChange={handleChange} required/>              </MDBCol>
            </MDBRow>
            <MDBInput name='email' wrapperClass='mb-4' label='Email' size='lg' id='formEmail' type='email' value={formData.email} onChange={handleChange} required/>
            <MDBInput name='address' wrapperClass='mb-4' label='Address' size='lg' id='formAddress' type='text' value={formData.address} onChange={handleChange} required/>
            <div className='mb-4'>
              <label className='form-label'>Gender</label>
              <div className='form-check'>
                <input className='form-check-input' type='radio' name='gender' id='male' value='male' onChange={handleChange} required/>
                <label className='form-check-label' htmlFor='male'>Male</label>
              </div>
              <div className='form-check'>
                <input className='form-check-input' type='radio' name='gender' id='female' value='female' onChange={handleChange} required/>
                <label className='form-check-label' htmlFor='female'>Female</label>
              </div>
            </div>
            <div className='mb-4'>
              <label className='form-label'>Age Group</label>
              <div className='form-check'>
                <input className='form-check-input' type='radio' id='ageGroup1' name='ageGroup' value='18-30' onChange={handleChange} checked={formData.ageGroup === '18-30'} required/>
                <label className='form-check-label' htmlFor='ageGroup1'>18-30</label>
              </div>
              <div className='form-check'>
                <input className='form-check-input' type='radio' id='ageGroup2' name='ageGroup' value='31-50' onChange={handleChange} checked={formData.ageGroup === '31-50'} required/>
                <label className='form-check-label' htmlFor='ageGroup2'>31-50</label>
              </div>
              <div className='form-check'>
                <input className='form-check-input' type='radio' id='ageGroup3' name='ageGroup' value='51+' onChange={handleChange} checked={formData.ageGroup === '51+'} required/>
                <label className='form-check-label' htmlFor='ageGroup3'>51+</label>
              </div>
            </div>
            <div className='mb-4'>
              <label className='form-label'>Date of Birth</label>
              <input className='form-control' type='date' name='dob' value={formData.dob} onChange={handleChange} required />
            </div>
            <div className='mb-4'>
              <label className='form-label'>Country</label>
              <select className='form-select' name='country' onChange={handleChange} value={formData.country} required>
                <option value=''>Select Country</option>
                <option value='USA'>USA</option>
                <option value='Canada'>Canada</option>
              </select>
            </div>

            <ToastContainer>
              
            </ToastContainer>
            <MDBBtn className='mb-4 w-100 gradient-custom-4' size='lg' type='submit'>Register</MDBBtn>
          </form>
          {submittedDate && (
            <div className="text-center mt-4">
              <p className="mb-0">Form Submitted on: {submittedDate.toLocaleString()}</p>
            </div>
          )}
        </MDBCardBody>
      </MDBCard>
    </MDBContainer>
  );
}
export default Form;
