import React from 'react';

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container-fluid">
        <a className="navbar-brand" href="/">Home</a>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item">
              <a className="nav-link" aria-current="page" href="/">Home</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/view">View</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/import">Import</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/upload">Upload</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/form">Form</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/formview">Form-View</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
