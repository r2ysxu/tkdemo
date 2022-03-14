import React from 'react';
import { useEffect } from 'react';

function ScrollLoader({children, loadMore, hasMore, height}) {

    const loadUntilScrollable = () => {
        const scrollHeight = (document.documentElement
          && document.documentElement.scrollHeight)
          || document.body.scrollHeight;
        if (window.innerHeight === scrollHeight && hasMore) {
            loadMore();
        }
    }

    const onScrollLoad = () => {
        const scrollTop = (document.documentElement
          && document.documentElement.scrollTop)
          || document.body.scrollTop;
        const scrollHeight = (document.documentElement
          && document.documentElement.scrollHeight)
          || document.body.scrollHeight;
        if (scrollTop + window.innerHeight + height >= scrollHeight && hasMore) {
            loadMore();
        }
    }

    useEffect(() => {
        loadUntilScrollable();
        window.addEventListener('scroll', onScrollLoad);
        return () => window.removeEventListener('scroll', onScrollLoad);
    });

    return (
        <div>{children}</div>
    )
}

export default ScrollLoader;