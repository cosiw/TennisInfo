import React from 'react';
import styles from './TopTen.module.css'; 
function TopTen({ranking}){
    return(
        <div>
        <h1>Ranking from Spring Boot:</h1>
        <div className={styles.cardContainer}>
          {ranking.map((rank, index) => (
            <div className={styles.card} key={index}>
              <h2>{rank.rankName}</h2>
              <p>Score: {rank.score}</p>
            </div>
          ))}
        </div>
      </div>
    )
}

export default TopTen;