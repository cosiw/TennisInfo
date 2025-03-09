import React, { useEffect, useState } from 'react';
import axios from 'axios';
import TopTen from './components/TopTen';
function App() {
  const [ranking, setRanking] = useState([]);
  console.log("hihi");
  useEffect(() => {
    axios.get('http://localhost:8085/api/rank/rankList')
      .then(response => {
        setRanking(response.data)
        console.log(response.data);})
      .catch(error => console.log(error))
  }, []);
 
  return (
    <div>
    <h1>Ranking from Spring Boot:</h1>
    <div className="card-container">
      {ranking.map((rank, index) => (
        <div className="card" key={index}>
          <h2>{rank.rankName}</h2>
          <p>PlayerName: {rank.playerName}</p>
        </div>
      ))}
    </div>
    <TopTen ranking={ranking}/>
  </div>

  );
}
 
export default App;
