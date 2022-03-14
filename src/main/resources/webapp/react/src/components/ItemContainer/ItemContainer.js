import React from 'react';
import { RelayEnvironmentProvider } from 'react-relay/hooks';
import RelayEnvironment from '../../RelayEnvironment';
import ListInputControl from '../ListInputControl/ListInputControl';

const { Suspense } = React;

function ItemContainer() {
    return (
        <RelayEnvironmentProvider environment={RelayEnvironment}>
            <Suspense fallback={'Loading...'}>
                <ListInputControl
                    label="Top 3 Priorities"
                    placeholder="Enter"
                    required={true}
                    disabled={false}
                    max={10}
                />
            </Suspense>
        </RelayEnvironmentProvider>
    );
}

export default ItemContainer;