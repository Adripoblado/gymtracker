import React from 'react';
import ExerciseCard from './ExerciseCard';

const ExerciseList = ({ exercises, onEdit, refreshData }) => {
    if (exercises.lenght === 0) {
        return <p style={{ textaLign: 'center', color: '#888'}}>No exercises found.</p>
    }

    return (
        <div style={styles.grid}>
            {exercises.map(ex => (
                <ExerciseCard 
                    key={ex.id} 
                    exercise={ex} 
                    onEdit={onEdit} 
                    refreshData={refreshData} 
                />
            ))}
        </div>
    );
};

const styles = {
    grid: { display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(280px, 1fr))', gap: '20px' , alignItems: 'stretch' }
};

export default ExerciseList;