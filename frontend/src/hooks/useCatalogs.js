import { useQuery } from '@tanstack/react-query';
import api from '../services/api';

const STALE_TIME = Infinity;

export const useCatalogs = () => {
    const muscleGroups = useQuery({
        queryKey: ['muscleGroups'],
        queryFn: () => api.get('/api/catalogs/muscle-groups').then(res => res.data),
        staleTime: STALE_TIME
    });

    const exerciseTypes = useQuery({
        queryKey: ['exerciseTypes'],
        queryFn: () => api.get('/api/catalogs/exercise-types').then(res => res.data),
        staleTime: STALE_TIME
    });

    const equipments = useQuery({
        queryKey: ['equipments'],
        queryFn: () => api.get('/api/catalogs/equipments').then(res => res.data),
        staleTime: STALE_TIME
    });

    return {
        muscleGroups: muscleGroups.data || [],
        exerciseTypes: exerciseTypes.data || [],
        equipments: equipments.data || [],
        isLoading: muscleGroups.isLoading || exerciseTypes.isLoading || equipments.isLoading,
        isError: muscleGroups.isError || exerciseTypes.isError || equipments.isError
    };
};