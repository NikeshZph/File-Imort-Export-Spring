import React from 'react';

const HomePage = () => {
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 mt-4">
        <img src="https://cdn.mos.cms.futurecdn.net/PT5pFAAGxFtjqxZV9Q7DW3-650-80.jpg.webp" alt="Description" className="img-fluid" />
        </div>
        <div className="col-md-6 mt-4">
          <h2>Description</h2>
          <p>

          For a website system, integrating MySQL data with Excel can streamline various tasks like data analysis, reporting, and data manipulation. Uploading MySQL data into Excel allows website administrators to extract relevant information from the database and present it in a user-friendly format. This data can include user analytics, transaction records, or any other pertinent information stored in the MySQL database. Conversely, uploading Excel data into MySQL enables administrators to update website content, manage user data, or import external data sources into the website system efficiently. By bridging the gap between MySQL and Excel, website systems can enhance their functionality, enabling administrators to make data-driven decisions and maintain the website effectively. This integration empowers website administrators with the flexibility to leverage the strengths of both MySQL and Excel, ultimately improving the performance and usability of the website system          </p>
          <button className="btn btn-primary">Learn More</button>
        </div>
      </div>
    </div>
  );
}

export default HomePage;