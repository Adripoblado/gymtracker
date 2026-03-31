import React from 'react';
import WorkoutCard from './WorkoutCard.jsx';

const WorkoutList = ({ workouts, onEdit, refreshData }) => {
    if (workouts.length === 0) {
        return <p style={{ textAlign: 'center', color: '#888'}}>No workouts found.</p>
    }

    return(
        <div style={styles.grid}>
            {workouts.map(workout => (
                <WorkoutCard 
                    key={workout.id} 
                    workout={workout} 
                    onEdit={onEdit} 
                    refreshData={refreshData} />
            ))}
        </div>
    );
};

const styles = {
    grid: { display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(280px, 1fr))', gap: '20px' , alignItems: 'stretch' }
};

export default WorkoutList;