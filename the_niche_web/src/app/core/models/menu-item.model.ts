

export interface MenuItem {
    id: string;
    title: string;
    icon: string;
    route: string;
    badge?: string;
    children?: MenuItem[];
}

export interface UserProfile {
    name: string;
    email: string;
    avatar?: string;
    role: string;
}

export interface DashboardStats {
    title: string;
    value: string | number;
    icon: string;
    color: string;
    trend?: {
        value: number;
        isPositive: boolean;
    };
}
