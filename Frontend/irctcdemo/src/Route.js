import React, { useState } from 'react';
import './Header.css';

const Route = () => {

    const [data, setData] = useState([]);
    const [error, setError] = useState(null);
    const [showTable, setShowTable] = useState(false);
    const [routeId, setRouteId] = useState('');
    const [routeData, setRouteData] = useState(null);

    const fetchData = async () => {
        try {
            const response = await fetch('http://localhost:8082/infyrail/routes/allroutes');
            console.log('New Response is : ', response);
            if (!response.ok) {
                throw new Error('Network Response Was not Ok.');
            }
            const result = await response.json();
            setData(result);
            setShowTable(true);
        } catch (error) {
            setError(error.message);
        }
    };

    const handleCancel = () => {
        setShowTable(false);
    };

    // Handle search by route ID
    const handleSearchClick = async () => {
        try {
            const response = await fetch(`http://localhost:8082/infyrail/routes/${routeId}`);
            if (!response.ok) {
                throw new Error('Network Response Was not Ok.');
            }
            const result = await response.json();
            setRouteData(result);
        } catch (error) {
            setError(error.message);
        }
    };

    // Handle input change for route ID
    const handleInputChange = (e) => {
        setRouteId(e.target.value);
    };

    return (
        <body className="App-header">

            <header class="main-header">
                <div class="logo-container">
                    <a href="#default" class="logo">
                        <img src='https://www.shutterstock.com/image-vector/vector-retro-train-logo-on-260nw-534313327.jpg' alt="Logo" />
                    </a>
                </div>
                <nav class="nav-menu">
                    <a class="nav-link active" href="#home">Home</a>
                    <a class="nav-link" href="#contact">Contact</a>
                    <a class="nav-link" href="#about">About</a>
                </nav>
            </header>

            <div className="userName">
                <div className="welcomeText">Welcome to</div>
                <div className="nameText">Bangaru Kranthi Kiran</div>
            </div>


            <h1 className="tittle-header">Book a Train Ticket</h1>


            <div>
                <div className="form-container">
                    <input type="text" placeholder="Enter From location" className="text-input" />
                    <input type="text" placeholder="Enter To location" className="text-input" />
                    <input type="text" placeholder="select Date" className="text-input" />
                    <input type="text" placeholder="Number of Tickets" className="text-input" />
                </div>
                <div>
                    <button className="submit-button">Search</button>
                </div>

            </div>

            <div className="Add-containers">
                <h5>Add a train</h5> <button className="click-here-button-train">Click Here</button>
                <h5>Add a Route</h5> <button className="click-here-button-route">Click Here</button>
            </div>
            <div className="main-container">
                <div className="existing-ui">
                    <div className="all-Routes">
                        <h5 className="side-header">Show the List of Trains</h5>
                    </div>
                    <div className="click-Button">
                        <button className="routs-ListButton" onClick={fetchData}>Click Here...</button>
                    </div>
                </div>
                {error && <p>Error: {error}</p>}

            </div>
            {showTable && (  // Conditionally render the table
                <div className="data-container">
                    <button className="cancel-button" onClick={handleCancel}>X</button> {/* Cancel Button */}
                    <table className="data-table">
                        <thead>
                            <tr>
                                <th>Source</th>
                                <th>Destination</th>
                            </tr>
                        </thead>
                        <tbody>
                            {data.map(item => (
                                <tr key={item.id}>
                                    <td>{item.source}</td>
                                    <td>{item.destination}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}


            <div>
                <div className="route-Id">
                    <h5 className="route-Id-header">Check Route Id Status</h5>
                    <input type="text" placeholder="Enter Route Id Number" className="text-input" value={routeId}
                        onChange={handleInputChange} />
                    <button className="routs-IdButton" onClick={handleSearchClick}>Search</button>
                </div>
            </div>
            {routeData && (
                <div className="route-data">
                    <h3>Route Details</h3>
                    <p><strong>Source:</strong> {routeData.source}</p>
                    <p><strong>Destination:</strong> {routeData.destination}</p>
                    <h4>Trains</h4>
                    <table>
                        <thead>
                            <tr>
                                <th>Train ID</th>
                                <th>Train Name</th>
                                <th>Arrival Time</th>
                                <th>Departure Time</th>
                                <th>Fare</th>
                            </tr>
                        </thead>
                        <tbody>
                            {routeData.trains.map(train => (
                                <tr key={train.id}>
                                    <td>{train.id}</td>
                                    <td>{train.trainName}</td>
                                    <td>{train.arrivalTime}</td>
                                    <td>{train.departureTime}</td>
                                    <td>{train.fare}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}

            <footer class="main-footer">
                <div class="footer-bottom">
                    <p>© 2024 Train Booking Service. All rights reserved.</p>
                </div>
            </footer>

        </body>
    );
}

export default Route;
