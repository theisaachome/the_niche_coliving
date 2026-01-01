


export interface ModalActionConfig {
    label: string;
    type?: 'approve' | 'cancel' | 'neutral' | 'submit'; // Fomantic button style
    callback?: () => void; // function executed when clicked
}

export  class BaseModalConfig {
    title='Default Title';
    actions:ModalActionConfig[]=[]
}
